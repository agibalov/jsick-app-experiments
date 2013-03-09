package com.loki2302.dom;

public interface DOMTypeDescriptorVisitor<T> {
	T visitNamedTypeDescriptor(DOMNamedTypeDescriptor namedTypeDescriptor);
}