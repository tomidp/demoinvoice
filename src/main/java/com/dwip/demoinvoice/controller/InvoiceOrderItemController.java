package com.dwip.demoinvoice.controller;

import com.dwip.demoinvoice.entity.InvoiceOrderItem;
import com.dwip.demoinvoice.repository.InvoiceOrderItemRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/invoiceOrderItem")
public class InvoiceOrderItemController {

    private final InvoiceOrderItemRepository invoiceOrderItemRepository;

    @Autowired
    public InvoiceOrderItemController(InvoiceOrderItemRepository invoiceOrderItemRepository) {
        this.invoiceOrderItemRepository = invoiceOrderItemRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<InvoiceOrderItem> getInvoiceOrderItems(@RequestParam String invoiceId) {
        return this.invoiceOrderItemRepository.findAllByInvoiceIdAndMarkForDelete(invoiceId, false);
    }

    @RequestMapping(value = "/csv", method = RequestMethod.GET)
    public List<InvoiceOrderItem> getCsv(@RequestParam String invoiceId) {
        List<InvoiceOrderItem> invoiceOrderItems = this.invoiceOrderItemRepository.findAllByInvoiceIdAndMarkForDelete(invoiceId, false);
        FileWriter out = null;
        CSVPrinter printer = null;
        try {
            out = new FileWriter("D:\\RESEARCH\\invoice.csv");
            printer = new CSVPrinter(out, CSVFormat.DEFAULT);
            for (InvoiceOrderItem invoiceOrderItem : invoiceOrderItems) {
                printer.printRecord(invoiceOrderItem.getOrderId(), invoiceOrderItem.getOrderItemId());
            }
        } catch (IOException e) {
            System.out.println("Exception");
        } finally {
            try {
                out.flush();
                out.close();
                printer.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }
        return invoiceOrderItems;
    }


}
