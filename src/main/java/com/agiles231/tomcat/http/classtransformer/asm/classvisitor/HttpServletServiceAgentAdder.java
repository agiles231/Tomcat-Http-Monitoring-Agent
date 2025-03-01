package com.agiles231.tomcat.http.classtransformer.asm.classvisitor;


import com.agiles231.tomcat.http.classtransformer.asm.methodvisitor.HttpServletServiceMethodAgentAdder;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * Add notifyRequestStart call on agent to service method, transfer id to HttpServletResponse (parameter 2 of service).
 *
 * Requirements to avoid runtime error: HttpServletResponse object must implement AgentIdContainer
 */
public class HttpServletServiceAgentAdder extends ClassVisitor {
    public HttpServletServiceAgentAdder(int api, ClassVisitor cv) {
        super(api, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (mv != null && name.equals("service") && desc.equals("(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;)V")) {
            mv = new HttpServletServiceMethodAgentAdder(this.api, mv);
        }
        return mv;

    }

}
