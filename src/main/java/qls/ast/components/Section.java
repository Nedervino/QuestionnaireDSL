package qls.ast.components;

import ql.ast.SourceLocation;
import qls.ast.defaultrules.DefaultRule;
import qls.ast.visitors.ComponentVisitor;

import java.util.ArrayList;
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

    public List<QuestionReference> getQuestionReferences() {
        return new ArrayList<>();
    }

    public String getSectionId() {
        return sectionId;
    }

    public List<Component> getComponents() {
        return components;
    }

    public List<DefaultRule> getRules() {
        return rules;
    }

    @Override
    public <T> T accept(ComponentVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
