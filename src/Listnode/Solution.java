package Listnode;

public class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        if(head.val==val){
            return head.next;
        }
        ListNode p=head;
        while (p.next!=null&&p.next.val!=val){
            p=p.next;
        }
        if(p.next!=null)p.next=p.next.next;
        return head;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
