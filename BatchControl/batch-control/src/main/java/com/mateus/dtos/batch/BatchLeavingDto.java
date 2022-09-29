package com.mateus.dtos.batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchLeavingDto {

    private String productName;

    private List<String> idBatch = new ArrayList<>();

    public void setIdBatch(String idBatch){
        this.idBatch.add(idBatch);
    }
}
