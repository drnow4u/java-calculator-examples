package co.uk.cogitolearning.cogpar;

import co.uk.cogitolearning.cogpar.tree.*;
import org.junit.Assert;
import org.junit.Test;


public class CalculatorTest {
    @Test
    public void shouldCalculateSimpleOperation() {
        // (2+3)*4
        ConstantNode two = new ConstantNode(2);
        ConstantNode three = new ConstantNode(3);
        ConstantNode four = new ConstantNode(4);

        AdditionNode sum = new AdditionNode(two, three);
        MultiplicationNode multi = new MultiplicationNode(sum, four);

        Assert.assertEquals(20.0, Calculator.calculateTree(multi), 0.1);
    }

    @Test
    public void shouldCalculateExtendedCalculation() {
        ExpressionNode root;

        // String exprstr = "6*(3+sin(pi/2))^5"; //=6144
        ConstantNode five = new ConstantNode(6);

        VariableNode pi = new VariableNode("pi");
        DivNode halfPi = new DivNode(pi, new ConstantNode(2));

        FunctionNode sin = new FunctionNode(FunctionNode.SIN, halfPi);
        AdditionNode braces = new AdditionNode(new ConstantNode(3), sin);
        ExponentiationNode exponent = new ExponentiationNode(braces, new ConstantNode(5));
        MultiplicationNode multiplication = new MultiplicationNode(five, exponent);

        root = multiplication;

        Algorithms.setVariable(root, "pi", Math.PI);

        Assert.assertEquals(6144.0, Calculator.calculateTree(root), 0.1);
    }

    @Test
    public void shouldParse() {
        Assert.assertEquals(8.0, Calculator.calculate("2*(1+sin(pi/2))^2"), 0.1);
    }

    @Test
    public void shouldParseSub() {
        Assert.assertEquals(3 - 1, Calculator.calculate("3-1"), 0.1);
    }

    @Test
    public void shouldParseSub2() {
        Assert.assertEquals(4 - 1 - 2, Calculator.calculate("4-1-2"), 0.1);
    }

    @Test
    public void shouldParseAdd2() {
        Assert.assertEquals(1 + 2 + 3, Calculator.calculate("1+2+3"), 0.1);
    }

}
