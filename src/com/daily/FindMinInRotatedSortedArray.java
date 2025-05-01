package com.daily;

/**
 * 扇形数组中的最小值查找（二分查找法）
 *
 * @author Az
 * @date 2025/5/1
 */
public class FindMinInRotatedSortedArray {
    public static void main(String[] args) {
        int[] test1 = {4, 5, 6, 7, 0, 1, 2};
//        System.out.println(findMin(test1));
        int[] test2 = {3, 4, 5, 1, 2};
        System.out.println(findMin(test2));

    }

    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
