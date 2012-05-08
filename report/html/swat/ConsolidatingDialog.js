var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":229,"id":120,"methods":[{"el":42,"sc":2,"sl":40},{"el":52,"sc":2,"sl":45},{"el":78,"sc":4,"sl":68},{"el":102,"sc":4,"sl":91},{"el":124,"sc":4,"sl":117},{"el":133,"sc":4,"sl":130},{"el":139,"sc":2,"sl":54},{"el":153,"sc":2,"sl":141},{"el":164,"sc":2,"sl":155},{"el":199,"sc":2,"sl":166},{"el":227,"sc":2,"sl":201}],"name":"ConsolidatingDialog","sl":35}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = $jsonSrcFileLines
