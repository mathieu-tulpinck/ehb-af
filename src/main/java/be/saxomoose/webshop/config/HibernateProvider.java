package be.saxomoose.webshop.config;

import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.boot.model.relational.Sequence;
import org.hibernate.mapping.Table;
import org.hibernate.tool.schema.spi.SchemaFilter;
import org.hibernate.tool.schema.spi.SchemaFilterProvider;

public class HibernateProvider implements SchemaFilterProvider {
    @Override
    public SchemaFilter getCreateFilter() {
        return MySchemaFilter.INSTANCE;
    }
    @Override
    public SchemaFilter getDropFilter() {
        return MySchemaFilter.INSTANCE;
    }
    @Override
    public SchemaFilter getMigrateFilter() {
        return MySchemaFilter.INSTANCE;
    }
    @Override
    public SchemaFilter getValidateFilter() {
        return MySchemaFilter.INSTANCE;
    }
}
class MySchemaFilter implements SchemaFilter {
    public static final MySchemaFilter INSTANCE = new MySchemaFilter();
    @Override
    public boolean includeNamespace(Namespace namespace) {
        return true;
    }
    @Override
    public boolean includeTable(Table table) {
        if (table.getName().equalsIgnoreCase("users")){
            return false;
        }
        return true;
    }
    @Override
    public boolean includeSequence(Sequence sequence) {
        return true;
    }
}
