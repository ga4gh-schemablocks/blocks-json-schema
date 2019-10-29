.. _rstupdate:

======
Update
======

A class for holding data about an update event to a record. This is used to hold brief details about when it occurred
and optionally who or what made the update and any pertinent information regarding the content and/or reason for the
update.

**Data model**


.. list-table:: Definition  of the ``Gene`` element
   :widths: 25 25 50 50
   :header-rows: 1

   * - Field
     - Type
     - Status
     - Description
   * - timestamp
     - `ISO8601 UTC timestamp <https://en.wikipedia.org/wiki/ISO_8601>`_
     - required
     - ISO8601 UTC timestamp at which this record was updated
   * - updated_by
     - string
     - optional
     - Information about the person/organisation/network that has updated the phenopacket
   * - comment
     - string
     - required
     - Textual comment about the changes made to the content and/or reason for the update.


**Example**

.. code-block:: json

  {
    "timestamp": "2018-06-10T10:59:06Z"
  }

Optionally, with extra information:

.. code-block:: json

  {
    "timestamp": "2018-06-10T10:59:06Z",
    "updated_by": "Julius J.",
    "comment": "added phenotypic features to individual patient:1"
  }

timestamp
~~~~~~~~~
An `ISO8601 UTC timestamp <https://en.wikipedia.org/wiki/ISO_8601>`_ for when this phenopacket was updated.

updated_by
~~~~~~~~~~
Information about the person/organisation/network that has updated the phenopacket.

comment
~~~~~~~
Textual comment about the changes made to the content and/or reason for the update. This should be a brief description
only, it is not the actual update.