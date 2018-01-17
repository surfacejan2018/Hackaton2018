package com.example.entities;

import net.corda.core.identity.CordaX500Name;
import net.corda.core.serialization.CordaSerializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@CordaSerializable
public class NewIOUMessage {
    @XmlElement public CordaX500Name partyName;
    @XmlElement public int iouValue;
    @XmlElement public String etfname;
    @XmlElement public String quantity;
    @XmlElement public String price;
    @XmlElement public String sponsor;
    @XmlElement public String action;
    @XmlElement public String limit;

    @Override
    public String toString() {
        return "NewIOUMessage{" +
                "partyName='" + partyName + '\'' +
                ", iouValue=" + iouValue +
                ", etfname='" + etfname + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", action='" + action + '\'' +
                ", limit='" + limit + '\'' +
                '}';
    }
}
