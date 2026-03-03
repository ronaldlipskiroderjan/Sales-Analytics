package com.ronald.salesanalytics.service;

import com.ronald.salesanalytics.model.Sale;
import com.ronald.salesanalytics.repository.SaleRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ExcelService {

    private final SaleRepository repository;

    // Injeção de dependência via constructor
    public ExcelService(SaleRepository repository) {
        this.repository = repository;
    }

    public ByteArrayInputStream gerarRelatorioExcel() {
        // 1. Buscar todos os dados do banco
        List<Sale> vendas = repository.findAll();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // 2. Criar a aba da planilha
            Sheet sheet = workbook.createSheet("Relatório de Vendas");

            // 3. Criar o Cabeçalho (Linha 0)
            Row headerRow  = sheet.createRow(0);
            String[] colunas = {"ID", "Produto", "Valor (R$)", "Data de Venda"};

            //Loop simples para preencher o cabeçalho
            for (int i = 0; i < colunas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(colunas[i]);


                // Estilo extra: deixar negrito
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // 4. Preencher os dados
            int rowIdx = 1;
            for (Sale venda : vendas) {
                Row row  = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(venda.getId());
                row.createCell(1).setCellValue(venda.getProduto());
                row.createCell(2).setCellValue(venda.getValor());
                row.createCell(3).setCellValue(venda.getDataVenda().toString());
            }

            // Escreve tudo no output
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        }catch (Exception e){
            // Se der erro, joga no console pra gente ver
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar Excel: " + e.getMessage());
        }
    }
}
