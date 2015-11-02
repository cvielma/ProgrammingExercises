/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeetCode;

/**
 * https://leetcode.com/problems/gas-station/
 * @author cvielma
 */
public class GasStations {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int[] diff = new int[gas.length];

	// Find difference (O(n))
	int totalSum = 0;
	for (int i = 0; i < diff.length; i++) {
		diff[i] = gas[i] - cost[i];
		totalSum += diff[i];
	}

	// It's not possible to do the circuit.
	if (totalSum < 0) {
		return -1;
	}

	// Find negative (O(n))
	int negPos = -1;
	for (int i = 0; i < diff.length; i++) {
		if (diff[i] < 0) {
			negPos = i;
			break;
		}
	}
	
	// No negatives, return first gas station (as good as any)
	if (negPos == -1) {
		return 0;
	}

	// Else, we keep two pointers: posPos and negPos
	// posPos is going to keep track of the the index on which the sum starts to be positive. negPos keeps track of the index in which the sum became negative.
	// When currSum is >= 0, we increase negPos index. When currSum < 0 we decrease posPos (looking for a previous positive number to make the sum >= 0).
	int posPos = (negPos - 1) % diff.length;
        posPos = posPos < 0 ? diff.length + posPos : posPos;
	int currSum = diff[negPos] + diff[posPos];
	while (posPos != negPos) {
		if (currSum >= 0) {
			negPos = (negPos + 1) % diff.length;
			currSum += diff[negPos];
		} else {
			posPos = (posPos - 1) % diff.length;
			currSum += diff[posPos];
		}
	}
	return posPos;
    }
    
    public static void main(String[] args) {
        int[] gas = {1, 2};
        int[] cost = {2, 1};
        
        System.out.println(canCompleteCircuit(gas, cost));
    }
}
