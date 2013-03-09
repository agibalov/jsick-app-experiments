package com.loki2302.dom;

public interface DOMTypeDescriptor extends DOMElement {    
	<T> T accept(DOMTypeDescriptorVisitor<T> visitor);
}