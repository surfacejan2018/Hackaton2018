package com.example.schema;

import com.google.common.collect.ImmutableList;
import net.corda.core.schemas.MappedSchema;
import net.corda.core.schemas.PersistentState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * An IOUState schema.
 */
public class IOUSchemaV1 extends MappedSchema {
    public IOUSchemaV1() {
        super(IOUSchema.class, 1, ImmutableList.of(PersistentIOU.class));
    }

    @Entity
    @Table(name = "iou_states")
    public static class PersistentIOU extends PersistentState {
        @Column(name = "lender") private final String lender;
        @Column(name = "borrower") private final String borrower;
        @Column(name = "value") private final int value;
        @Column(name = "etfname") private final String etfname;
        @Column(name = "quantity") private final Integer quantity;
        @Column(name = "price") private final Integer price;
        @Column(name = "sponsor") private final String sponsor;
        @Column(name = "action") private final String action;
        @Column(name = "linear_id") private final UUID linearId;


        public PersistentIOU(String lender, String borrower, int value, String petfname, Integer quantity, Integer price, String sponsor, String action, UUID linearId) {
            this.lender = lender;
            this.borrower = borrower;
            this.value = value;
            this.etfname = petfname;
            this.quantity = quantity;
            this.price = price;
            this.sponsor = sponsor;
            this.action = action;
            this.linearId = linearId;
        }

        public String getLender() {
            return lender;
        }

        public String getBorrower() {
            return borrower;
        }

        public int getValue() {
            return value;
        }

        public String getEtfname() {
			return etfname;
		}

		public UUID getId() {
            return linearId;
        }

		public Integer getQuantity() {
			return quantity;
		}

		public Integer getPrice() {
			return price;
		}

		public String getSponsor() {
			return sponsor;
		}

		public String getAction() {
			return action;
		}
    }
}