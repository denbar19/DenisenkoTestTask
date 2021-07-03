**Akvelon Coding task**
Brackets balance verification
(aka HackerRank stack problem)

A bracket is considered to be one of the following characters: (), {}, [].

Brackets are considered matched pair if it occurs that every open bracket has closing bracket of the exact the same type (‘(’, ‘{’, ‘[’), ie. 
1.	‘()’, ‘{}’, ‘[]’ - balanced, open and closed brackets exist
2.	‘(}’ - not balanced, open and closed brackets has different type
3.	‘((())’ - not balanced, number of open brackets does not equal to the number of closing ones;

A matching pair of brackets is not balanced when the set of brackets it encloses are not matched, ie. ({[(]}) is not balanced because the contents between [ and ] are not balanced. The pair of square brackets contains a single unbalanced bracket ‘(’ between them.

**Functional requirements:**
The sequence of brackets is balanced when the following conditions are met:

●	It contains no unmatched brackets.

●	The subset of brackets enclosed within the confines of a matched pair of brackets is also a matched pair of brackets.

**Constraints:**

●	All characters in the sequences ∈ { {, }, (, ), [, ] }.

●	A validation message should be present when a string contains non-brackets value

**Functional Description:**
Application implements BalanceVerificator class with a single method
Input: string value
Output: integer value returns  -1 - when balanced and number of bracket when not balanced
Acceptance Criteria:
Given a string of brackets, determine if the line of brackets is balanced print - BALANCED and return -1, otherwise print - NOT BALANCED and return a number of not balanced brackets in the line.

Example of NOT BALANCED number:
({[(]}) - bracket with number 4 is not balanced, so expected result will be NOT BALANCED(4) 
   ↑ - number 4 should be returned by application

**Testing Requirements:**
Application should have at least 5 unit-tests

**Code-Style conventions**
Application should contains code documentation, code should be clear, self-documented and follow Google Style Guides


Sample:

1.Passed:
Input: {[()]}
Output: BALANCED, returns -1
Explanation: The string {[()]} meets both criteria for being a balanced string.

2. Not Passed
Input: {[(]}
Explanation: The string is not balanced, because the brackets enclosed by the matched pair { and } are not balanced [(]
Output: NOT BALANCED (3), returns 3

3. Passed
Input:  {{[[(())]]}}
Output: BALANCED, returns -1
Explanation: The string {{[[(())]]}} matches both functional criteria

4. Not Passed:
Input: s[]
Output: A character ‘s’ doesn’t belong to any known brackets type, returns IllegalArgumentException.
Explanation: The string contains ‘s’ character that doesn’t belong to bracket types ([{ or }])

