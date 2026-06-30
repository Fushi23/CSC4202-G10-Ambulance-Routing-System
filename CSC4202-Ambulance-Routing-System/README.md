# CSC4202 Ambulance Routing System in UPM

A Design and Analysis of Algorithms (CSC4202, Semester II 2025/2026) group project that
applies **Dijkstra's Algorithm** to find the fastest emergency route across the Universiti
Putra Malaysia (UPM) campus during a fire emergency.

## Group 10 Members

| No. | Name | Matric Number |
| --- | --- | --- |
| 1 | Muhammad Irfan Daniel Bin Md Sham | 226089 |
| 2 | Muhammad Aidil Qayyim Bin Din | 223555 |
| 3 | Zahin Zulqarnain Bin Zulkifli | 222292 |
| 4 | Muhammad Aiman Haziq Bin Mohd Salleh | 222707 |

**Lecturer:** Dr Nur Arzilawati Md Yunus

## Overview

The campus road network is modelled as a **weighted undirected graph** of 10 locations
(nodes) joined by 13 roads (edges). Each edge weight is an *effective travel cost*, obtained
by multiplying the road distance in kilometres by a traffic multiplier that reflects how
congested the road is. Given a fire at KOSASS B and heavy congestion from a football
tournament near KTDI, the program computes the lowest-cost route for an ambulance leaving
Hospital Sultan Abdul Aziz Shah (HSAAS).

- **Source:** HSAAS (node A)
- **Destination:** KOSASS B (node J)
- **Optimal route found:** A → C → F → H → J (HSAAS → Pusat Sukan → Bukit Ekspo → KPZ → KOSASS B)
- **Total effective travel cost:** 6.55

## Algorithm

Topic: Graph Algorithms, Single-Source Shortest Path.
Solution: **Dijkstra's Algorithm**, implemented with an adjacency list and a min-priority
queue (Java's built-in `PriorityQueue`).

- Time complexity: **O((V + E) log V)**
- Space complexity: **O(V + E)**

BFS, Floyd-Warshall and Greedy Best-First Search were also reviewed; Dijkstra was chosen
because it accounts for edge weights, solves only the single source-to-destination route we
need, and always returns the optimal path on graphs with non-negative weights.

## Repository Layout

| Folder / file | Contents |
| --- | --- |
| `src/` | Java implementation (`AmbulanceRouting.java`) |
| `report/` | Full project report (PDF) |
| `presentation/` | Presentation slide deck (PDF) |
| `outputs/` | Reserved for program output screenshots |
| `Project Appendix group 10.pdf` | Project planning and progress appendix |

## Running the Program

Make sure a Java Development Kit (JDK 8 or newer) is installed, then from the project root:

```bash
cd src
javac AmbulanceRouting.java
java AmbulanceRouting
```

The program prints the shortest effective cost from HSAAS to every node, the chosen optimal
route, the total cost, and the measured execution time.
