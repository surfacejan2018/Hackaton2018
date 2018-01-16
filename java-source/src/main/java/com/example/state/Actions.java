package com.example.state;

import net.corda.core.serialization.CordaSerializable;

@CordaSerializable
public enum Actions {
	CREATE, REDEEM;
}
