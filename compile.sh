#!/usr/bin/env bash

if [ -e simulation.txt ]
then
  rm simulation.txt
fi

find * -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java src.lsandor.simulator.Simulator scenario.txt

if [ -e simulation.txt ]
then
    printf "Simulation.txt file was created."
else
    printf "Error: simulation.txt file was not created"
fi