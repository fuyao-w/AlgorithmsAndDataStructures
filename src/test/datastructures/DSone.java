package test.datastructures;

import org.junit.Test;

import java.util.*;

/**
 * @author wangfy
 * @Description TODO
 * @date 2019/3/8
 **/
public class DSone {
    /**
     * @return
     * @date 2019/3/8 17:36
     * @Description 373. 查找和最小的K对数字
     * @Param [nums1, nums2, k]
     **/
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new LinkedList<>();
        if (nums1.length == 0 || nums2.length == 0) {
            return res;
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        int n1 = nums1.length;
        int n2 = nums2.length;
        for (int i = 0; i < n2; i++) {
            heap.offer(new int[]{nums1[0] + nums2[i], 0, i});
        }
        while (k > 0 && heap.size() > 0) {
            int[] tmp = heap.poll();
            res.add(new int[]{nums1[tmp[1]], nums2[tmp[2]]});
            if (tmp[1] + 1 < n1) {
                heap.offer(new int[]{nums1[tmp[1] + 1] + nums2[tmp[2]], tmp[1] + 1, tmp[2]});
            }
            k--;
        }
        return res;
    }

    /**
     * @return test.datastructures.TreeNode
     * @date 2019/3/20 9:37
     * @Description 236. 二叉树的最近公共祖先
     * @Param [root, p, q]
     **/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p == root || q == root) {
            return p == root ? p : q;
        }
        TreeNode left = null;
        TreeNode right = null;
        if (root.left != null) {
            left = lowestCommonAncestor(root.left, p, q);
        }
        if (root.right != null) {
            right = lowestCommonAncestor(root.right, p, q);
        }


        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;

    }

    /**
     * @date 2019/3/20 12:04
     * @return int
     * @Description 375. 猜数字大小 II
     * @Param [n]
     **/
    public int getMoneyAmount(int n) {

        int dp[][] = new int[n + 1][n + 1];
        for (int lo = n - 1; lo >= 1; lo--) {
            for (int hi = lo + 1; hi <= n; hi++) {
                dp[lo][hi] = Integer.MAX_VALUE;
                for (int idx = lo; idx < hi; idx++) {
                    System.out.format("%d,%d\n",lo,hi);
                    dp[lo][hi] = Math.min(dp[lo][hi], idx + Math.max(dp[lo][idx - 1], dp[idx + 1][hi]));
                }
            }
        }
        return dp[1][n];
    }

    @Test
    public void test() {
        getMoneyAmount(10);
    }

}