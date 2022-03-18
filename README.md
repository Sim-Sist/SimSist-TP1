# SimSist-TP1
```bash
chmod +x src/run.sh
```
and then
```bash
./src/run.sh
```
to generate output files in folder _src/main/output_

The output files have the following formats:

**particles.txt:**

##### Header
- Number of particles
- Size of Space
- Length of critical radius
- One free line to put a comment/name. This can also be left blank.

##### Body
- Body of textfile consists of one line for each particle with its radius and then its xy coordinates, all separated by a spaces

**neighbours.txt:**

##### Header
- One free line to put a comment/name. This can also be left blank.

##### Body
- Body of textfile consists of one line for each particle with a list of all its neighbours separated by a space
