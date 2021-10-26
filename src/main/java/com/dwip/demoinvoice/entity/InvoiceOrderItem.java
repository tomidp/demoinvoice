package com.dwip.demoinvoice.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Document(collection = InvoiceOrderItem.COLLECTION_NAME)
public class InvoiceOrderItem extends BaseMongoEntity {
    public static final String COLLECTION_NAME = "invoice_order_item";
    private static final long serialVersionUID = 5991189612001638135L;

    private String orderId;
    private String orderItemId;
    private String skuCode;
    private String itemName;
    private int itemQuantity;
    private String commissionType;
    private String unitType = "Pcs";
    private String endCustomerName;
    private BigDecimal itemPrice;
    private boolean ppnTaxable;
    private BigDecimal dppPpn;
    private BigDecimal ppnValue;
    private boolean pph22Taxable;
    private BigDecimal dppPph22;
    private BigDecimal pph22Value;
    private BigDecimal totalTax;
    private BigDecimal discount;
    private BigDecimal shippingCost;
    private BigDecimal shippingInsuranceCost;
    private BigDecimal shippingDiscount;
    private BigDecimal totalAmount;
    private String invoiceId;
    private Boolean markForDelete;

    public void calculateTotalTax() {
        if (ppnTaxable) {
            totalTax = ppnValue.multiply(BigDecimal.valueOf(itemQuantity));
        } else if (pph22Taxable) {
            totalTax = pph22Value.multiply(BigDecimal.valueOf(itemQuantity));
        } else {
            totalTax = BigDecimal.ZERO;
        }
    }

    public void calculateTotalAmount() {
        if (ppnTaxable) {
            BigDecimal totalDppPpn = dppPpn.multiply(BigDecimal.valueOf(itemQuantity));
            BigDecimal totalPpnValue = ppnValue.multiply(BigDecimal.valueOf(itemQuantity));
            totalAmount = totalDppPpn.add(totalPpnValue).subtract(discount);
        } else if (pph22Taxable) {
            BigDecimal totalDppPph22 = dppPph22.multiply(BigDecimal.valueOf(itemQuantity));
            BigDecimal totalPph22Value = pph22Value.multiply(BigDecimal.valueOf(itemQuantity));
            totalAmount = totalDppPph22.add(totalPph22Value).subtract(discount);
        } else {
            totalAmount = itemPrice.multiply(BigDecimal.valueOf(itemQuantity)).subtract(discount);
        }
    }
}
