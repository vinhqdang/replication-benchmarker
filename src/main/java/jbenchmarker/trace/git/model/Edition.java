/*
 * represent a block od edits.
 */
package jbenchmarker.trace.git.model;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jgit.diff.Edit;
import org.eclipse.jgit.diff.Edit.Type;
import org.eclipse.jgit.diff.RawText;

/**
 *
 * @author urso
 */
public class Edition {
    protected int beginA, endA, beginB, endB;
    protected List<String> ca;
    protected List<String> cb;
    protected Type type;

    public Edition() {
    }

    public int getBeginA() {
        return beginA;
    }

    public void setBeginA(int beginA) {
        this.beginA = beginA;
    }

    public int getBeginB() {
        return beginB;
    }

    public void setBeginB(int beginB) {
        this.beginB = beginB;
    }

    public List<String> getCa() {
        return ca;
    }

    public void setCa(List<String> ca) {
        this.ca = ca;
    }

    public List<String> getCb() {
        return cb;
    }

    public void setCb(List<String> cb) {
        this.cb = cb;
    }

    public int getEndA() {
        return endA;
    }

    public void setEndA(int endA) {
        this.endA = endA;
    }

    public int getEndB() {
        return endB;
    }

    public void setEndB(int endB) {
        this.endB = endB;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Edition(Edit edit, RawText a, RawText b) {
        this.beginA = edit.getBeginA();
        this.endA = edit.getEndA();
        this.beginB = edit.getBeginB();
        this.endB = edit.getEndB();
        this.type = edit.getType();
        this.ca = new ArrayList<String>();
        this.cb = new ArrayList<String>();
        for (int i = beginA; i < endA; ++i) {
            ca.add(a.getString(i) + '\n');
        }
        for (int i = beginB; i < endB; ++i) {
            cb.add(b.getString(i) + '\n');
        }
    }

    @Override
    public String toString() {
        StringBuilder  s = new StringBuilder();
        for (int i = this.getBeginA(); i < this.getEndA(); ++i) {
            s.append("--- (").append(i).append(") ").append(ca.get(i-this.getBeginA())).append('\n');
        }
        for (int i = this.getBeginB(); i < this.getEndB(); ++i) {
            s.append("+++ (").append(i).append(") ").append(cb.get(i-this.getBeginB())).append('\n');
        }
        return s.toString();
    }
}