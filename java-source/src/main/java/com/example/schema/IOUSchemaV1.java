package com.example.schema;

import com.example.state.Actions;
import com.google.common.collect.ImmutableList;
import net.corda.core.schemas.MappedSchema;
import net.corda.core.schemas.PersistentState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.UUID;

/**
 * An IOUState schema.
 */
public class IOUSchemaV1 extends MappedSchema implements Serializable {
	
	private static final long serialVersionUID = 12L;

	public IOUSchemaV1() {
        super(IOUSchema.class, 1, ImmutableList.of(PersistentIOU.class));
    }

    @Entity
    @Table(name = "iou_states_second")
    public static class PersistentIOU extends PersistentState {
        @Column(name = "lender") private final String lender;
        @Column(name = "borrower") private final String borrower;
        @Column(name = "value") private final int value;
        @Column(name = "linear_id") private final UUID linearId;

        @Column(name = "etfname", nullable = true) private final String ETFName;
        @Column(name = "quantity", nullable = true) private final Double quantity;
        @Column(name = "price", nullable = true) private final Double price;
        @Column(name = "sponsor", nullable = true) private final String sponsor;
        @Column(name = "action", nullable = true) private final String action;
        @Column(name = "limit", nullable = true) private final Double limit;
        
        public PersistentIOU(String lender, String borrower, int value, UUID linearId, String eTFName, Double quantity,
				Double price, String sponsor, String action, Double limit) {
			super();
			this.lender = lender;
			this.borrower = borrower;
			this.value = value;
			this.linearId = linearId;
			ETFName = eTFName;
			this.quantity = quantity;
			this.price = price;
			this.sponsor = sponsor;
			this.action = (action != null ? action.toString() : null);
			this.limit = limit;
		}

        public UUID getLinearId() {
			return linearId;
		}
		public String getETFName() {
			return ETFName;
		}
		public Double getQuantity() {
			return quantity;
		}
		public Double getPrice() {
			return price;
		}
		public String getSponsor() {
			return sponsor;
		}
		public String getAction() {
			return action;
		}
		public Double getLimit() {
			return limit;
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
        public UUID getId() {
            return linearId;
        }
        
    }
}