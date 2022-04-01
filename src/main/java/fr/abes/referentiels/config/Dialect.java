package fr.abes.referentiels.config;

import org.hibernate.dialect.Oracle12cDialect;
import org.hibernate.tool.schema.extract.internal.SequenceInformationExtractorNoOpImpl;
import org.hibernate.tool.schema.extract.spi.SequenceInformationExtractor;

/*
Classe qui permet d'éviter l'erreur suivante :
https://stackoverflow.com/questions/58570032/hibernate-could-not-fetch-the-sequenceinformation-from-the-database
https://www.javafixing.com/2022/01/fixed-hibernate-could-not-fetch.html
 */
public class Dialect extends Oracle12cDialect {
    @Override
    public SequenceInformationExtractor getSequenceInformationExtractor() {
        return SequenceInformationExtractorNoOpImpl.INSTANCE;
    }

    @Override
    public String getQuerySequencesString() {
        return "select * from all_sequences";
    }
}