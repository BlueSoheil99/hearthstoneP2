package edu.sharif.student.bluesoheil.ap98.hearthstone.Util.Configuration.LogicConfigs;

public abstract class LogicConfig {

    public LogicConfig() {
        setProperties();
        initialize();
    }

    protected abstract void setProperties();

    protected abstract void initialize();
}
