package com.example.awesomefat.csc300_spring2018_linkedlist;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by awesomefat on 1/30/18.
 */

public class LinkedList
{
    private Node head;
    private ViewGroup linkedListContainer;
    private Context theContext;

    public LinkedList(ViewGroup linkedListContainer, Context theContext)
    {
        this.head = null;
        this.linkedListContainer = linkedListContainer;
        this.theContext = theContext;
    }

    public int removeFront() throws Exception
    {
        //removes the front of the linked list, and updates the interface
        //and ultimately returns the int payload of the node that was removed.
        if(this.head != null)
        {
            Node node2Remove = this.head;
            this.head = this.head.getNextNode();
            node2Remove.setNextNode(null);
            this.linkedListContainer.removeViewAt(0); // removes the view at 0
            return node2Remove.getPayload();
        }
        else
        {
            Toast.makeText(this.theContext, "List is Empty", Toast.LENGTH_SHORT).show();
            throw new Exception("Empty List");
        }
    }

    public int removeEnd() throws Exception
    {
        //removes the end of the linked list, and updates the interface
        //and ultimately returns the int payload of the node that was removed.
        if(this.head != null)
        {
            //traverse the list to find the node right before the last node
            Node node2Remove = this.head;
            while(node2Remove.getNextNode() != null)
            {
                node2Remove = node2Remove.getNextNode();
            }
            //we know that node2Remove points to the very last node
            //find the node that points to node2Remove
            if(node2Remove == this.head)
            {
                //we have a one list
                this.head = null;
            }
            else
            {
                Node prevNode = this.head;
                while(prevNode.getNextNode() != node2Remove)
                {
                    prevNode = prevNode.getNextNode();
                }
                //prevNode points to the node right before node2Remove
                prevNode.setNextNode(null);

            }
            this.linkedListContainer.removeViewAt(this.linkedListContainer.getChildCount()-1);
            return node2Remove.getPayload();
        }
        else
        {
            //we have an empty list
            Toast.makeText(this.theContext,"Empty List", Toast.LENGTH_SHORT).show();
            throw new Exception("Empty List");
        }
    }

    public int removeAtIndex(int index) throws Exception
    {
        if(this.head != null)
        {
            Node temp = this.head;
            int payload = 0;
            //set temp to the element before index
            for(int i = 0; i < index; i++)
            {
                if(temp.getNextNode() == null)
                {
                    try
                    {
                        payload = this.removeEnd();
                    }
                    catch(Exception e){System.out.println("I'm honestly not sure how this happened!");}
                    return payload;
                }
                temp = temp.getNextNode();
            }
            //store the payload of the index character (temp + 1)
            payload = temp.getNextNode().getPayload();
            //set temp's next node to the node after the node at index (temp + 2)
            temp.setNextNode(temp.getNextNode().getNextNode());
            this.linkedListContainer.removeViewAt(index);
            return payload;

        }
        else
        {
            //we have an empty list
            Toast.makeText(this.theContext,"Empty List", Toast.LENGTH_SHORT).show();
            throw new Exception("Empty List");
        }
    }

    public int altRemoveIndex(int index) throws Exception
    {
        if(head != null)
        {
            Node temp = this.head;
            for(int i = 0; i < index; i++)
            {
                if(temp.getNextNode() == null)
                {
                    break;
                }
                temp = temp.getNextNode();
            }
            return temp.removeNext();
        }
        else
        {
            //error stuff
            Toast.makeText(this.theContext,"Empty List", Toast.LENGTH_SHORT).show();
            throw new Exception("Empty List");
        }
    }

    public void addFront(int payload)
    {
        Node n = new Node(payload);

        //add to front of list
        if(this.head == null)
        {
            this.head = n;
        }
        else
        {
            //not dealing with the empty list
            n.setNextNode(this.head);
            this.head = n;
        }

        //update the interface
        TextView tv = new TextView(this.theContext);
        tv.setText("" + payload);
        tv.setGravity(Gravity.CENTER);
        this.linkedListContainer.addView(tv,0);
    }

    public void addEnd(int payload)
    {
        if(this.head == null)
        {
            this.addFront(payload);
        }
        else
        {
            this.head.addEnd(payload);
            /*
            Node n = new Node(payload);
            Node currNode = this.head;

            while(currNode.getNextNode() != null)
            {
                currNode = currNode.getNextNode();
            }
            //currnode is sitting at the last node
            currNode.setNextNode(n);
            */

            //update the interface
            TextView tv = new TextView(this.theContext);
            tv.setText("" + payload);
            tv.setGravity(Gravity.CENTER);
            this.linkedListContainer.addView(tv);
        }
    }

    //counts up to the indexth entry in the list, and adds a new node.
    //this will displace the list forward, such that the new node is now the indexth node.
    public void addAtIndex(int index, int payload)
    {
        Node temp = this.head;
        //iterate temp to be the node before element at index
        for(int i = 0; i < index; i++)
        {
            if(temp.getNextNode() == null)
            {
                this.addEnd(payload);
                return;
            }
            temp = temp.getNextNode();
        }
        Node newGuy = new Node(payload);
        newGuy.setNextNode(temp.getNextNode());
        temp.setNextNode(newGuy);

        TextView tv = new TextView(this.theContext);
        tv.setText("" + payload);
        tv.setGravity(Gravity.CENTER);
        this.linkedListContainer.addView(tv, index);
    }

    public void altAddIndex(int index, int payload)
    {
        Node temp = this.head;
        for(int i = 0; i < index; i++)
        {
            if(temp.getNextNode() == null)
            {
                break;
            }
            temp = temp.getNextNode();
        }
        temp.addNext(payload);
    }

    public void display()
    {
        if(this.head == null)
        {
            System.out.println("Empty List");
        }
        else
        {
            //do stuff here
            this.head.display();
            System.out.println("");
            /*
            String answer = "";
            Node currNode = this.head;
            while(currNode != null)
            {
                answer = answer + currNode.getPayload() + " -> ";
                currNode = currNode.getNextNode();
            }
            System.out.println(answer);
            */

        }
    }
}
