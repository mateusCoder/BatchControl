package com.mateus.services.impl;

import com.google.common.collect.Lists;
import com.mateus.dtos.batch.*;
import com.mateus.entities.Batch;
import com.mateus.entities.Product;
import com.mateus.exceptions.ObjectNotFound;
import com.mateus.exceptions.UnprocessableEntity;
import com.mateus.repositories.BatchRepository;
import com.mateus.repositories.ProductRepository;
import com.mateus.services.BatchService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;

    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    @Override
    public Page<BatchDto> findAll(Long id, Pageable page) {
        checkProductExistence(id);
        Page<Batch> batches = batchRepository.findByProductId(id, page);
        return batches.map(e -> mapper.map(e, BatchDto.class));
    }

    @Override
    public URI create(Long id, BatchFormPostDto batchFormPostDto) {
        Batch batch = mapper.map(batchFormPostDto, Batch.class);
        if(batchRepository.findById(batch.getId()).isPresent()){
            sumExistencesBatch(batch.getId(), batch.getAmount());
        }else{
            Product product = checkProductExistence(id);
            batch.setProductId(product.getId());
            batchRepository.save(batch);

            product.setBatches(batch);
            productRepository.save(product);
        }
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(batch.getId());
    }

    @Override
    public BatchLeavingDto updateFromRetail(Long id, BatchFormPutFromRetailDto batchFormPutFromRetailDto) {
        BatchLeavingDto batchLeavingDto = new BatchLeavingDto();
        Integer amount = batchFormPutFromRetailDto.getAmount();
        List<Batch> batches = findBatchesWithAmountByProduct(id);
        Integer totalAmountBatches = 0;

        for(Batch batch: batches){
            totalAmountBatches += batch.getAmount();
        }

        if(totalAmountBatches >= batchFormPutFromRetailDto.getAmount()){
            List<String> idBatches = subtractAmountBatch(amount, batches);
            batchLeavingDto.setProductName(checkProductExistence(id).getName());
            idBatches.forEach(e ->batchLeavingDto.setIdBatch(e));
        }else{
            throw new UnprocessableEntity("Insufficient Amount to Process");
        }

        return batchLeavingDto;
    }

    @Override
    public BatchLeavingDto updateFromWholesale(Long id, BatchFormPutFromWholesaleDto batchFormPutFromWholesaleDto) {
        BatchLeavingDto batchLeavingDto = new BatchLeavingDto();
        batchLeavingDto.setProductName(checkProductExistence(id).getName());
        List<Batch> batchesForSale = findBatchesWithAmountByProduct(id).stream().filter(
                e -> e.getAmount() == batchFormPutFromWholesaleDto.getProductsPerBatch()).collect(Collectors.toList());

        if(batchesForSale.size() == batchFormPutFromWholesaleDto.getNumberBatches()){
            batchesForSale.forEach(e -> e.setAmount(0));
            batchesForSale.forEach(batchRepository::save);
            batchesForSale.forEach(e -> batchLeavingDto.setIdBatch(e.getId()));

        } else if (batchesForSale.size() >= batchFormPutFromWholesaleDto.getNumberBatches()){
            List<List<Batch>> subLists = Lists.partition(batchesForSale, batchFormPutFromWholesaleDto.getNumberBatches());
            subLists.get(0).forEach(e -> e.setAmount(0));
            subLists.get(0).forEach(batchRepository::save);
            subLists.get(0).forEach(e -> batchLeavingDto.setIdBatch(e.getId()));

        } else {
            throw new UnprocessableEntity("Insufficient Amount to Process");
        }

        return batchLeavingDto;
    }

    public Batch checkBatchExistence(String id){
        return batchRepository.findById(id).orElseThrow(() ->new ObjectNotFound("Batch Not Found!"));
    }

    public Product checkProductExistence(Long id){
        return productRepository.findById(id).orElseThrow(() ->new ObjectNotFound("Product Not Found!"));
    }

    protected void sumExistencesBatch(String id, Integer amount) {
        Batch batch = checkBatchExistence(id);
        Integer actualAmount = batch.getAmount();
        batch.setAmount(actualAmount + amount);
        batchRepository.save(batch);
    }

    protected List<Batch> findBatchesWithAmountByProduct(Long id) {
        Product product = checkProductExistence(id);
        System.out.println(product.getBatches());
        List<Batch> batches = product.getBatches();
        List<Batch> batchesWithAmount = batches.stream().filter(e -> e.getAmount() > 0).collect(Collectors.toList());
        batchesWithAmount.sort((d1, d2) -> d1.getExpirationDate().compareTo(d2.getExpirationDate()));

        return batchesWithAmount;
    }

    protected List<String> subtractAmountBatch(Integer amount, List<Batch> batches){
        List idBatches = new ArrayList<>();
        int i = 0;
        while (amount > 0){
            Integer amountBatch = batches.get(i).getAmount();
            Batch batch = checkBatchExistence(batches.get(i).getId());

            if(amount > amountBatch){
                batch.setAmount(0);
                amount-=amountBatch;
                idBatches.add(batch.getId());
            }else{
                batch.setAmount(amountBatch - amount);
                amount-=amount;
                idBatches.add(batch.getId());
            }
            batchRepository.save(batch);
            i++;
        }
        return  idBatches;
    }
}
