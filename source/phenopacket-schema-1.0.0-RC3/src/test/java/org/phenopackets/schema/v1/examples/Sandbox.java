package org.phenopackets.schema.v1.examples;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.JsonFormat;
import org.junit.jupiter.api.Test;
import org.phenopackets.schema.v1.Phenopacket;
import org.phenopackets.schema.v1.core.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sandbox {



    @Test
    void testTimeStamp() {
        long millis  = System.currentTimeMillis();
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(millis / 1000)
                .setNanos((int) ((millis % 1000) * 1000000)).build();

    }


    @Test
    void testSpecificData() throws Exception {
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String hastings = "1066-10-14";
        Date date = formatter.parse(hastings);
        long millis = date.getTime();
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(millis / 1000)
                .setNanos((int) ((millis % 1000) * 1000000)).build();
        System.out.println(JsonFormat.printer().includingDefaultValueFields().print(timestamp));
    }


    @Test
    void testEvidence() throws Exception {
        OntologyClass publishedClinicalStudy = OntologyClass.
                newBuilder().
                setId("ECO:0006017").
                setLabel("author statement from published clinical study used in manual assertion").
                build();
        ExternalReference reference = ExternalReference.newBuilder().
                setId("PMID:20375004").
                setDescription("Mutations in fibrillin-1 cause congenital scleroderma: stiff skin syndrome").
                build();
        Evidence evidence = Evidence.newBuilder().
                setEvidenceCode(publishedClinicalStudy).
                setReference(reference).
                build();
        System.out.println(JsonFormat.printer().includingDefaultValueFields().print(evidence));
    }


    @Test
    void testProcedure() throws Exception {
        OntologyClass biopsySoftPalate = OntologyClass.newBuilder().
                setId("NCIT:C51585").
                setLabel("Biopsy of Soft Palate").
                build();
        Procedure procedure1 = Procedure.newBuilder().
                setCode(biopsySoftPalate).
                build();
        Biosample bs = Biosample.newBuilder().
                setProcedure(procedure1).
                build();
        System.out.println(JsonFormat.printer().includingDefaultValueFields().print(bs));

        OntologyClass punchBiopsy = OntologyClass.newBuilder().
                setId("NCIT:C28743").
                setLabel("Punch Biopsy").
                build();
        OntologyClass skinOfForearm = OntologyClass.newBuilder().
                setId("UBERON:0003403").
                setLabel("skin of forearm").
                build();
        Procedure procedure2 = Procedure.newBuilder().
                setCode(punchBiopsy).
                setBodySite(skinOfForearm).
                build();
        System.out.println(JsonFormat.printer()
                .print(Biosample.newBuilder().
                        setProcedure(procedure2).
                        build()));
    }

    @Test
    void testDisease() throws Exception{
        OntologyClass HPMRS1 = OntologyClass.newBuilder().
                setId("OMIM:239300").
                setLabel("Hyperphosphatasia with mental retardation syndrome 1").
                build();
        OntologyClass neonatalOnset = OntologyClass.newBuilder().
                setId("HP:0003623").
                setLabel("Neonatal onset").
                build();
        Disease disease = Disease.newBuilder().
                setTerm(HPMRS1).
                setClassOfOnset(neonatalOnset).
                build();
        Disease disease2 = Disease.newBuilder().
                setTerm(HPMRS1).
                setClassOfOnset(neonatalOnset).
                build();
        Phenopacket pp = Phenopacket.newBuilder().
                addDiseases(disease).
                addDiseases(disease2).
                build();
        System.out.println(JsonFormat.printer().includingDefaultValueFields().print(pp));

    }


    @Test void test1() {
        Phenopacket phenoPacket = Phenopacket.newBuilder().build();
        try {
            System.out.println(JsonFormat.printer().includingDefaultValueFields().print(phenoPacket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test void test22() throws IOException {
        OntologyClass biopsySoftPalate = OntologyClass.newBuilder().
                setId("NCIT:C51585").
                setLabel("Biopsy of Soft Palate").
                build();
        String JSONString = JsonFormat.printer().includingDefaultValueFields().print(biopsySoftPalate);
        JsonNode jsonNodeTree = new ObjectMapper().readTree(JSONString);
        String yamlString = new YAMLMapper().writeValueAsString(jsonNodeTree);
        System.out.println(yamlString);

        biopsySoftPalate.writeTo(System.out);


    }


}
