State Machine
=============

Simple and multipurpose state machine engine

# Concepts

## States and Stimuli

This simple project implements a Finite State Machine based on a Stimulus/State table.

   | ***State / Stimulus*** | **0** | **1** |
   |------------------------|-------|-------|
   | *0*                    | 1     | -     |
   | *1*                    | 2     | -     |
   | *2*                    | -     | 0     |
   
Lines indicates *States* and columns indicates *Stimulus*.

The matching cell indicates the *next* state

Ex.:

Initial state = *1*

When we send Stimulus *0*, the machine goes to State *1*.

Since it is on State *1*, when we send Stimulus *0* again, then machine goes to state *2*.

Since it is on State *2*, when we send Stimulus *0* again, then machine stay on state *2*.

Since it is still on State *2*, when we send Stimulus *1* again, then machine goes back to State *0*.


## State Actions

For each state we may attribute an *action*.

This action is started when the machine enters to its state.

The action may return the next Stimulus to be sent to machine after next state change.

# Code example

You can use test cases on this project as example for your use.
 
