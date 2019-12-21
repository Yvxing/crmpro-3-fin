package com.ujiuye.utils;

import com.ujiuye.pojo.Analysis;
import com.ujiuye.pojo.Module;
import com.ujiuye.pojo.Project;

import java.util.List;

public class AnalysisBean {
    private Analysis analysis;
    private Project project;
    List<Module> modules;

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
