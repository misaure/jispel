package com.msaure.jispel.memory.type;

import com.msaure.jispel.memory.Handle;
import com.msaure.jispel.memory.NodeValue;

public class NodeValueHandle extends Handle {
    
    private NodeValue value;
    
    public NodeValueHandle(NodeType nodeType) {
        super(nodeType);
    }
    
}
