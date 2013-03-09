package com.loki2302;

import com.loki2302.dom.DOMNamedTypeDescriptor;
import com.loki2302.dom.DOMTypeDescriptor;
import com.loki2302.dom.DOMTypeDescriptorVisitor;
import com.loki2302.evaluation.Context;
import com.loki2302.evaluation.TypeResult;
import com.loki2302.evaluation.UnknownTypeFailureReason;

public class DOMTypeDescriptorEvaluator {
    public TypeResult evaluateDOMTypeDescriptor(final Context context, DOMTypeDescriptor domTypeDescriptor) {
    	return domTypeDescriptor.accept(new DOMTypeDescriptorVisitor<TypeResult>() {
			public TypeResult visitNamedTypeDescriptor(DOMNamedTypeDescriptor namedTypeDescriptor) {
				String typeName = namedTypeDescriptor.getTypeName();
				if(typeName.equals("int")) {
					return TypeResult.ok(context.intType);
				} else if(typeName.equals("double")) {
					return TypeResult.ok(context.doubleType);
				}
				
				return TypeResult.fail(new UnknownTypeFailureReason());
			}    		
    	});
    }
}