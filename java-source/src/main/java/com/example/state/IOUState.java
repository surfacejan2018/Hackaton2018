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
    private final String etfname;
    private final Integer quantity;
    private final Integer price;
    private final String sponsor;
    private final String action;
    private final UniqueIdentifier linearId;

	/**
     * @param value the value of the IOU.
     * @param lender the party issuing the IOU.
     * @param borrower the party receiving and approving the IOU.
     */
    public IOUState(Integer value,
                    Party lender,
                    Party borrower,  Integer quantity, Integer price,
        			String sponsor, String action, String pEtfname)
    {
        this.value = value;
        this.lender = lender;
        this.borrower = borrower;
        this.etfname = pEtfname;
        this.quantity = quantity;
		this.price = price;
		this.sponsor = sponsor;
		this.action = action;
        this.linearId = new UniqueIdentifier();
    }

    public Integer getValue() { return value; }
    public Party getLender() { return lender; }
    public String getEtfname() {
		return etfname;
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

	public Party getBorrower() { return borrower; }
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
                    this.etfname,
                    this.quantity,
                    this.price,
                    this.sponsor,
                    this.action,
                    this.linearId.getId());
        } else {
            throw new IllegalArgumentException("Unrecognised schema $schema");
        }
    }

    @Override public Iterable<MappedSchema> supportedSchemas() {
        return ImmutableList.of(new IOUSchemaV1());
    }

    @Override
    public String toString() {
        return String.format("%s(iou=%s, lender=%s, borrower=%s, linearId=%s)", getClass().getSimpleName(), value, lender, borrower, linearId);
    }
}