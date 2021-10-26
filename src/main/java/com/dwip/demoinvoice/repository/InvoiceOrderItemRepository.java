package com.dwip.demoinvoice.repository;

import com.dwip.demoinvoice.entity.InvoiceOrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InvoiceOrderItemRepository extends MongoRepository<InvoiceOrderItem, String> {

    List<InvoiceOrderItem> findAllByInvoiceIdAndMarkForDelete(String invoiceId, Boolean markForDelete);

}
