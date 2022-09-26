INSERT INTO Product(name, description, active) VALUES
('garrafa', 'garrafa azul bonita', true),
('coberta', 'coberta vermelha', true),
('cabo usb', 'cabo usb 2.0', true),
('mouse', 'mouse gamer com luzes', true);

INSERT INTO Batch(id, description, expiration_Date, amount, product_id) VALUES
('ABADD2C', 'lote de mouses 22', '2022-10-10', 10, 2),
('ABAD', 'lote de cabos', '2022-10-10', 20, 2),
('AB', 'lote de frio', '2022-10-10', 50, 2),
('DD2C', 'lote azul', '2022-10-10', 30, 2);
