.. _rstrdexample:

================================
A complete example: Rare Disease
================================

We will now present a phenopacket that represents a family with one individual
affected by `Bethlem myopathy <https://www.ncbi.nlm.nih.gov/pubmed/30808312>`_.
We present each of the
sections of the Phenopacket in separate subsections for legibility. Recall that JSON data is represented as
as name/value pairs that are separated by commas (we show the trailing comma on all but the last name/value pair of the
Phenopacket).

We show how to create this phenopacket in Java :ref:`here<rstrarediseaseexamplejava>`.


COL6A1 mutation leading to Bethlem myopathy with recurrent hematuria: a case report
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
We present an example that summarizes the data presented in a
`case report <https://www.ncbi.nlm.nih.gov/pubmed/30808312>`_ about a boy with Bethlem myopathy. For simplicity's sake,
we have omitted some of the details, but it should be obvious how one would construct the full Phenopacket.


id
~~
The `id` field is an arbitrary identifier of the family. In this publication, the family is not refered to by
any special name because only one family is reported, but often one sees identifiers such as "family A", etc.

.. code-block:: json

    "id": "family",

proband
~~~~~~~
This is a :ref:`rstphenopacket` element that describes the proband in this case, a 14-year old boy.

.. code-block:: json

    "proband": {
        "id": "14 year-old boy",
        "subject": {
            "id": "14 year-old boy",
            "ageAtCollection": {
            "age": "P14Y"
        },
        "sex": "MALE"
        },


At this point in the :ref:`rstphenopacket` element, there follows a list of phenotypic observations,

.. code-block:: json

 "phenotypicFeatures": [{ ... }, { ... }, (...) , { ... } ]

We present each phenotype separately in the following.


The following block describes `Decreased fetal movement <https://hpo.jax.org/app/browse/term/HP:0001558>`_.

.. code-block:: json

    {
      "type": {
        "id": "HP:0001558",
        "label": "Decreased fetal movement"
      },
      "classOfOnset": {
        "id": "HP:0011461",
        "label": "Fetal onset"
      },
      "evidence": [{
        "evidenceCode": {
          "id": "ECO:0000033",
          "label": "author statement supported by traceable reference"
        },
        "reference": {
          "id": "PMID:30808312",
          "description": "COL6A1 mutation leading to Bethlem myopathy with recurrent hematuria: a case report."
        }
      }]
    }


This block refers to the fact that the authors reported that "Tests of ... cranial nerves function were normal".

.. code-block:: json

    , {
      "type": {
        "id": "HP:0031910",
        "label": "Abnormal cranial nerve physiology"
      },
      "negated": true,
      "evidence": [{
        "evidenceCode": {
          "id": "ECO:0000033",
          "label": "author statement supported by traceable reference"
        },
        "reference": {
          "id": "PMID:30808312",
          "description": "COL6A1 mutation leading to Bethlem myopathy with recurrent hematuria: a case report."
        }
      }]
    }
This block refers to recurrent gross hematuria which had occured beginning six months before admission
at age 14 years (We record the age as 14 years because more precise data is not presented).

.. code-block:: json

    {
      "type": {
        "id": "HP:0011463",
        "label": "Macroscopic hematuria"
      },
      "modifiers": [{
        "id": "HP:0031796",
        "label": "Recurrent"
      }],
      "ageOfOnset": {
        "age": "P14Y"
      },
      "evidence": [{
        "evidenceCode": {
          "id": "ECO:0000033",
          "label": "author statement supported by traceable reference"
        },
        "reference": {
          "id": "PMID:30808312",
          "description": "COL6A1 mutation leading to Bethlem myopathy with recurrent hematuria: a case report."
        }
      }]
    },

Finally, this block describe mild motor delay in childhood.

.. code-block:: json

    {
      "type": {
        "id": "HP:0001270",
        "label": "Motor delay"
      },
      "severity": {
        "id": "HP:0012825",
        "label": "Mild"
      },
      "classOfOnset": {
        "id": "HP:0011463",
        "label": "Childhood onset"
      }
    }],
    "variants": [{
      "hgvsAllele": {
        "hgvs": "NM_001848.2:c.877G\u003eA"
      },
      "zygosity": {
        "id": "GENO:0000135",
        "label": "heterozygous"
      }
    }]
  }


relatives
~~~~~~~~~
Each of the relatives can be added as a :ref:`phenopacket`. In this case, we add Phenopackets for the mother and father,
both of whom are health. Therefore, the corresponding phenopackets only have fields for ``id`` and ``sex``.

.. code-block:: json


  "relatives": [{
    "subject": {
      "id": "MOTHER",
      "sex": "FEMALE"
    }
  }, {
    "subject": {
      "id": "FATHER",
      "sex": "MALE"
    }
  }],


pedigree
~~~~~~~~
The :ref:`rstpedigree` object represents the information that is typically included in a PED file.
It is important that the identifiers are the same as those used for the Phenopackets.

.. code-block:: json


  "pedigree": {
    "persons": [{
      "individualId": "14 year-old boy",
      "paternalId": "FATHER",
      "maternalId": "MOTHER",
      "sex": "MALE",
      "affectedStatus": "AFFECTED"
    }, {
      "individualId": "MOTHER",
      "sex": "FEMALE",
      "affectedStatus": "UNAFFECTED"
    }, {
      "individualId": "FATHER",
      "sex": "MALE",
      "affectedStatus": "UNAFFECTED"
    }]
  },



metaData
~~~~~~~~
The :ref:`rstmetadata` is required to provide details about all of the ontologies and external references used
in the Phenopacket.

.. code-block:: json

  "metaData": {
    "created": "2019-04-04T13:49:22.827Z",
    "createdBy": "Peter R.",
    "resources": [{
      "id": "hp",
      "name": "human phenotype ontology",
      "url": "http://purl.obolibrary.org/obo/hp.owl",
      "version": "2018-03-08",
      "namespacePrefix": "HP",
      "iriPrefix": "http://purl.obolibrary.org/obo/HP_"
    }, {
      "id": "geno",
      "name": "Genotype Ontology",
      "url": "http://purl.obolibrary.org/obo/geno.owl",
      "version": "19-03-2018",
      "namespacePrefix": "GENO",
      "iriPrefix": "http://purl.obolibrary.org/obo/GENO_"
    }, {
      "id": "pubmed",
      "name": "PubMed",
      "namespacePrefix": "PMID",
      "iriPrefix": "https://www.ncbi.nlm.nih.gov/pubmed/"
    }],
    "externalReferences": [{
      "id": "PMID:30808312",
      "description": "Bao M, et al. COL6A1 mutation leading to Bethlem myopathy with recurrent hematuria: a case report. BMC Neurol. 2019;19(1):32."
    }]
  }
