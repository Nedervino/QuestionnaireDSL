package view;

import java.util.HashMap;

public class FormState {

    //TODO keeps track of current variable values. This object can then be combined with the input form to create a 'current state' of the form,
    //which the typechecker can read.

    HashMap<String, Object> varValues;

}
