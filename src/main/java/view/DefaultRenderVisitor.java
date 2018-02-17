package view;

import ql.QLBaseVisitor;
import ql.QLParser;

public class DefaultRenderVisitor<T> extends QLBaseVisitor<T> {

    FormView formView;

    public DefaultRenderVisitor(FormView formView){
        this.formView = formView;
    }

    @Override public T visitInput(QLParser.InputContext ctx) {
        formView.renderInput(ctx);
        return null;
    }

    @Override public T visitOutput(QLParser.OutputContext ctx) {
        formView.renderOutput(ctx);
        return null;
    }

}
