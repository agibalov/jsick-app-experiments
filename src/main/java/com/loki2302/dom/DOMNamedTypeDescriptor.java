package com.loki2302.dom;


public class DOMNamedTypeDescriptor implements DOMTypeDescriptor {
	private final String typeName;
	
	public DOMNamedTypeDescriptor(String typeName) {
		this.typeName = typeName;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public <T> T accept(DOMTypeDescriptorVisitor<T> visitor) {
		return visitor.visitNamedTypeDescriptor(this);
	}
}