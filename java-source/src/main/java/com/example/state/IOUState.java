package com.example.state;

import com.example.schema.IOUSchemaV1;
import com.google.common.collect.ImmutableList;
import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.schemas.MappedSchema;
import net.corda.core.schemas.PersistentState;
import net.corda.core.schemas.QueryableState;

import java.util.Arrays;
import java.util.List;

/**
 * The state object recording IOU agreements between two parties.
 *
 * A state must implement [ContractState] or one of its descendants.
 */
public class IOUState implements LinearState, QueryableState {
    private final Integer value;
    private final Party lender;
    private final Party borrower;
    private UniqueIdentifier linearId;
    
    private final String ETFName;
    private final Double quantity;
    private final Double price;
    private String sponsor;
    private Actions action;
    private Double limit;

    public IOUState(Integer value, Party lender, Party borrower, String eTFName,
			Double quantity, Double price, String sponsor, Actions action, Double limit) {
		super();
		this.value = value;
		this.lender = lender;
		this.borrower = borrower;
		this.ETFName = eTFName;
		this.quantity = quantity;
		this.price = price;
		this.sponsor = sponsor;
		this.action = action;
		this.limit = limit;
		this.linearId = new UniqueIdentifier();
	}

    public Integer getValue() { return value; }
    public Party getLender() { return lender; }
    public Party getBorrower() { return borrower; }
    public String getSponsor() {
		return sponsor;
	}
	public Actions getAction() {
		return action;
	}
	public Double getLimit() {
		return limit;
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
	public void setLinearId(UniqueIdentifier linearId) {
		this.linearId = linearId;
	}
	@Override public UniqueIdentifier getLinearId() { return linearId; }
    @Override public List<AbstractParty> getParticipants() {
        return Arrays.asList(lender, borrower);
    }

    @Override public PersistentState generateMappedObject(MappedSchema schema) {
        if (schema instanceof IOUSchemaV1) {
            return new IOUSchemaV1.PersistentIOU(
            		this.lender.getName().toString(),
            		this.borrower.getName().toString(),
            		this.value,
            		this.linearId.getId(),
            		this.ETFName,
            		this.quantity,
            		this.price,
            		this.sponsor,
            		this.action,
            		this.limit
            		);
        } else {
            throw new IllegalArgumentException("Unrecognised schema $schema");
        }
    }

    @Override public Iterable<MappedSchema> supportedSchemas() {
        return ImmutableList.of(new IOUSchemaV1());
    }

    @Override
    public String toString() {
        return String.format("%s(iou=%s, lender=%s, borrower=%s, linearId=%s, quantity=%d, price=%d, ETFname=%s, action=%s)", 
        		getClass().getSimpleName(), value, lender, borrower, linearId, quantity, price, ETFName, action.toString());
    }
}