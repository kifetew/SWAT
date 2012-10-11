var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":234,"id":262,"methods":[{"el":48,"sc":2,"sl":28},{"el":70,"sc":2,"sl":50},{"el":80,"sc":2,"sl":72},{"el":104,"sc":2,"sl":82},{"el":116,"sc":2,"sl":106},{"el":121,"sc":2,"sl":118},{"el":140,"sc":2,"sl":123},{"el":154,"sc":2,"sl":142},{"el":162,"sc":2,"sl":156},{"el":172,"sc":2,"sl":164},{"el":232,"sc":2,"sl":199}],"name":"ConsolidatingDialogTest","sl":26},{"el":197,"id":349,"methods":[{"el":179,"sc":3,"sl":177},{"el":184,"sc":3,"sl":181},{"el":196,"sc":3,"sl":186}],"name":"ConsolidatingDialogTest.BibtexEntryWrapper","sl":174}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_0":{"methods":[{"sl":106}],"name":"writeBibDbException","pass":true,"statements":[{"sl":108},{"sl":109},{"sl":110},{"sl":111}]},"test_1":{"methods":[{"sl":118}],"name":"mainTest","pass":true,"statements":[{"sl":120}]},"test_10":{"methods":[{"sl":72},{"sl":123},{"sl":156},{"sl":177},{"sl":186}],"name":"mergeBibFiles","pass":true,"statements":[{"sl":73},{"sl":74},{"sl":125},{"sl":126},{"sl":127},{"sl":129},{"sl":130},{"sl":132},{"sl":133},{"sl":135},{"sl":136},{"sl":138},{"sl":139},{"sl":157},{"sl":158},{"sl":159},{"sl":161},{"sl":178},{"sl":188},{"sl":190},{"sl":191},{"sl":192}]},"test_13":{"methods":[{"sl":142}],"name":"mergeBibFilesException","pass":true,"statements":[{"sl":144},{"sl":145},{"sl":147},{"sl":148},{"sl":149}]},"test_14":{"methods":[{"sl":50},{"sl":72},{"sl":156},{"sl":177},{"sl":186}],"name":"removeDuplicatesWithDuplication","pass":true,"statements":[{"sl":52},{"sl":53},{"sl":55},{"sl":56},{"sl":59},{"sl":62},{"sl":64},{"sl":65},{"sl":67},{"sl":68},{"sl":69},{"sl":73},{"sl":74},{"sl":157},{"sl":158},{"sl":159},{"sl":161},{"sl":178},{"sl":188},{"sl":190},{"sl":191},{"sl":192}]},"test_15":{"methods":[{"sl":72},{"sl":82},{"sl":156},{"sl":177},{"sl":186}],"name":"writeBibDb","pass":true,"statements":[{"sl":73},{"sl":74},{"sl":84},{"sl":85},{"sl":86},{"sl":88},{"sl":89},{"sl":91},{"sl":93},{"sl":94},{"sl":95},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":101},{"sl":103},{"sl":157},{"sl":158},{"sl":159},{"sl":161},{"sl":178},{"sl":188},{"sl":190},{"sl":191},{"sl":192}]},"test_16":{"methods":[{"sl":142}],"name":"mergeBibFilesException","pass":true,"statements":[{"sl":144},{"sl":145},{"sl":147},{"sl":148},{"sl":149}]},"test_17":{"methods":[{"sl":199}],"name":"searchBibFiles","pass":true,"statements":[{"sl":201},{"sl":202},{"sl":203},{"sl":204},{"sl":205},{"sl":206},{"sl":207},{"sl":208},{"sl":209},{"sl":210},{"sl":211},{"sl":212},{"sl":213},{"sl":217},{"sl":218},{"sl":219},{"sl":220},{"sl":222},{"sl":223},{"sl":224},{"sl":225},{"sl":226},{"sl":228},{"sl":229},{"sl":230},{"sl":231}]},"test_18":{"methods":[{"sl":50},{"sl":72},{"sl":156},{"sl":177},{"sl":186}],"name":"removeDuplicatesWithDuplication","pass":true,"statements":[{"sl":52},{"sl":53},{"sl":55},{"sl":56},{"sl":59},{"sl":62},{"sl":64},{"sl":65},{"sl":67},{"sl":68},{"sl":69},{"sl":73},{"sl":74},{"sl":157},{"sl":158},{"sl":159},{"sl":161},{"sl":178},{"sl":188},{"sl":190},{"sl":191},{"sl":192}]},"test_19":{"methods":[{"sl":72},{"sl":156},{"sl":177},{"sl":181},{"sl":186}],"name":"testShrinking","pass":true,"statements":[{"sl":73},{"sl":74},{"sl":157},{"sl":158},{"sl":159},{"sl":161},{"sl":178},{"sl":183},{"sl":188},{"sl":190},{"sl":191},{"sl":192}]},"test_2":{"methods":[{"sl":28}],"name":"removeDuplicatesWithNoDuplication","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":33},{"sl":34},{"sl":36},{"sl":37},{"sl":38},{"sl":41},{"sl":42},{"sl":44},{"sl":45},{"sl":46},{"sl":47}]},"test_20":{"methods":[{"sl":199}],"name":"searchBibFiles","pass":true,"statements":[{"sl":201},{"sl":202},{"sl":203},{"sl":204},{"sl":205},{"sl":206},{"sl":207},{"sl":208},{"sl":209},{"sl":210},{"sl":211},{"sl":212},{"sl":213},{"sl":217},{"sl":218},{"sl":219},{"sl":220},{"sl":222},{"sl":223},{"sl":224},{"sl":225},{"sl":226},{"sl":228},{"sl":229},{"sl":230},{"sl":231}]},"test_21":{"methods":[{"sl":106}],"name":"writeBibDbException","pass":true,"statements":[{"sl":108},{"sl":109},{"sl":110},{"sl":111}]},"test_22":{"methods":[{"sl":118}],"name":"mainTest","pass":true,"statements":[{"sl":120}]},"test_3":{"methods":[{"sl":28}],"name":"removeDuplicatesWithNoDuplication","pass":true,"statements":[{"sl":30},{"sl":31},{"sl":32},{"sl":33},{"sl":34},{"sl":36},{"sl":37},{"sl":38},{"sl":41},{"sl":42},{"sl":44},{"sl":45},{"sl":46},{"sl":47}]},"test_6":{"methods":[{"sl":72},{"sl":82},{"sl":156},{"sl":177},{"sl":186}],"name":"writeBibDb","pass":true,"statements":[{"sl":73},{"sl":74},{"sl":84},{"sl":85},{"sl":86},{"sl":88},{"sl":89},{"sl":91},{"sl":93},{"sl":94},{"sl":95},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":101},{"sl":103},{"sl":157},{"sl":158},{"sl":159},{"sl":161},{"sl":178},{"sl":188},{"sl":190},{"sl":191},{"sl":192}]},"test_7":{"methods":[{"sl":72},{"sl":123},{"sl":156},{"sl":177},{"sl":186}],"name":"mergeBibFiles","pass":true,"statements":[{"sl":73},{"sl":74},{"sl":125},{"sl":126},{"sl":127},{"sl":129},{"sl":130},{"sl":132},{"sl":133},{"sl":135},{"sl":136},{"sl":138},{"sl":139},{"sl":157},{"sl":158},{"sl":159},{"sl":161},{"sl":178},{"sl":188},{"sl":190},{"sl":191},{"sl":192}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [2, 3], [], [2, 3], [2, 3], [2, 3], [2, 3], [2, 3], [], [2, 3], [2, 3], [2, 3], [], [], [2, 3], [2, 3], [], [2, 3], [2, 3], [2, 3], [2, 3], [], [], [18, 14], [], [18, 14], [18, 14], [], [18, 14], [18, 14], [], [], [18, 14], [], [], [18, 14], [], [18, 14], [18, 14], [], [18, 14], [18, 14], [18, 14], [], [], [15, 6, 10, 19, 7, 18, 14], [15, 6, 10, 19, 7, 18, 14], [15, 6, 10, 19, 7, 18, 14], [], [], [], [], [], [], [], [15, 6], [], [15, 6], [15, 6], [15, 6], [], [15, 6], [15, 6], [], [15, 6], [], [15, 6], [15, 6], [15, 6], [], [15, 6], [15, 6], [15, 6], [15, 6], [15, 6], [], [15, 6], [], [], [0, 21], [], [0, 21], [0, 21], [0, 21], [0, 21], [], [], [], [], [], [], [1, 22], [], [1, 22], [], [], [10, 7], [], [10, 7], [10, 7], [10, 7], [], [10, 7], [10, 7], [], [10, 7], [10, 7], [], [10, 7], [10, 7], [], [10, 7], [10, 7], [], [], [16, 13], [], [16, 13], [16, 13], [], [16, 13], [16, 13], [16, 13], [], [], [], [], [], [], [15, 6, 10, 19, 7, 18, 14], [15, 6, 10, 19, 7, 18, 14], [15, 6, 10, 19, 7, 18, 14], [15, 6, 10, 19, 7, 18, 14], [], [15, 6, 10, 19, 7, 18, 14], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [15, 6, 10, 19, 7, 18, 14], [15, 6, 10, 19, 7, 18, 14], [], [], [19], [], [19], [], [], [15, 6, 10, 19, 7, 18, 14], [], [15, 6, 10, 19, 7, 18, 14], [], [15, 6, 10, 19, 7, 18, 14], [15, 6, 10, 19, 7, 18, 14], [15, 6, 10, 19, 7, 18, 14], [], [], [], [], [], [], [20, 17], [], [20, 17], [20, 17], [20, 17], [20, 17], [20, 17], [20, 17], [20, 17], [20, 17], [20, 17], [20, 17], [20, 17], [20, 17], [20, 17], [], [], [], [20, 17], [20, 17], [20, 17], [20, 17], [], [20, 17], [20, 17], [20, 17], [20, 17], [20, 17], [], [20, 17], [20, 17], [20, 17], [20, 17], [], [], []]
