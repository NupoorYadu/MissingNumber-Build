# Bugzilla Issue Template

## Issue Summary

**Title:** [BUG/ENHANCEMENT/TASK/DOCUMENTATION] - Brief description

## Issue Type

- [ ] BUG - Code defect or error
- [ ] ENHANCEMENT - Feature request or improvement
- [ ] TASK - Development task
- [ ] DOCUMENTATION - Documentation update

## Severity

- [ ] P1 - Critical (Blocks development)
- [ ] P2 - High (Major functionality broken)
- [ ] P3 - Medium (Workaround available)
- [ ] P4 - Low (Minor issue)

## Component

- [ ] Algorithm Implementation
- [ ] Testing
- [ ] Documentation
- [ ] Eclipse STS Integration
- [ ] Bugzilla Integration
- [ ] CI/CD Pipeline

## Description

### Problem Statement
Clear description of the issue or feature request.

### Expected Behavior
What should happen?

### Actual Behavior
What actually happens?

### Environment
- Java Version: 
- Maven Version:
- OS: Windows / Linux / macOS
- Eclipse STS Version: (if applicable)

## Steps to Reproduce (for bugs)

1. Step 1
2. Step 2
3. Step 3

## Screenshots/Logs

Attach relevant output, stack traces, or screenshots.

## Related Issues

Link to related Bugzilla issues or pull requests.

## Acceptance Criteria (for enhancements)

- [ ] Criterion 1
- [ ] Criterion 2
- [ ] Criterion 3

## Related Code

Point to specific methods or classes:
- `MissingNumber.findMissingByXor(int[] nums)`
- `MissingNumber.findMissingByMath(int[] nums)`

---

## Example Issues

### BUG Example

**Title:** BUG - XOR algorithm produces incorrect result for arrays > 50000 elements

**Component:** Algorithm Implementation

**Severity:** P2 - High

**Description:**

When running `findMissingByXor()` on an array exceeding 50000 elements, the 
function returns an incorrect missing number.

**Steps to Reproduce:**
1. Create array with 50001 elements (0 to 50000 except one missing number)
2. Call `findMissingByXor(largeArray)`
3. Result is incorrect

**Expected:** Correct missing number
**Actual:** Wrong missing number returned

---

### ENHANCEMENT Example

**Title:** ENHANCEMENT - Add logging for algorithm execution

**Component:** Algorithm Implementation

**Severity:** P3 - Medium

**Description:**

Add debug-level logging to track the execution of each algorithm, 
showing:
- Input array size
- Algorithm used
- Steps executed
- Result and timing

**Acceptance Criteria:**
- [ ] Logging added to all 5 algorithms
- [ ] Log level: DEBUG
- [ ] Performance impact minimal (< 1%)
- [ ] Tests verify logging occurs

---

### TASK Example

**Title:** TASK - Setup remote debugging for Eclipse STS

**Component:** Eclipse STS Integration

**Severity:** P2 - High

**Description:**

Configure Eclipse STS for remote debugging of Missing Number algorithms.

**Acceptance Criteria:**
- [ ] Launch configuration created
- [ ] Can set breakpoints
- [ ] Can step through code
- [ ] Variables visible during debug
- [ ] Documentation complete

---

