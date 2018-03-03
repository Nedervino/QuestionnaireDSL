package ql.ast.visitors;

import ql.ast.types.*;

public interface TypeVisitor<T> {

    T visit(BooleanType booleanType);

    T visit(DecimalType decimalType);

    T visit(IntegerType integerType);

    T visit(MoneyType moneyType);

    T visit(StringType stringType);

    T visit(DateType dateType);
    
}
