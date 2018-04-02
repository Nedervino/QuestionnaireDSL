package qls.ast.components;

import ql.ast.SourceLocation;
import qls.ast.defaultrules.DefaultRule;

import java.util.List;

public class Section extends Component {

    private final String sectionId;
    private final List<Component> components;
    private final List<DefaultRule> rules;

    public Section(String sectionId, List<Component> components, List<DefaultRule> rules, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.sectionId = sectionId;
        this.components = components;
        this.rules = rules;
    }
}
