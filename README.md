armadillo2
==========

This is the java version of Armadillo workflow (adn.bioinfo.uqam.ca/armadillo)

# armadillo2.0 and Docker 
armadillo2.0 proposed NGS programs that are running in Docker containers. \
Docker needs to be installed and Docker Daemon need to run. Windows only, Docker information can to be setted and tested in Armadillo Preferences or with the Docker button (top right of workflow framework) \
It has been tested on:
- ubuntu 14.04, 16.04 with Docker running in user session. (More instruction on https://askubuntu.com/a/477554)
- windows7 and lower with Docker Toolbox (More instruction on https://docs.docker.com/toolbox/toolbox_install_windows/)
- mac osX sierra version 10.12.6 and Docker Community Edition 17.09.0-ce-mac35 (More instruction on https://docs.docker.com/docker-for-mac/)

# armadillo2.0 and Cluster
armadillo2.0 proposed also a cluster workflow. \
It's using SSH key connexion to Calcul Québec Cluster. Cluster information can to be setted and tested in Armadillo Preferences or with the Cluster button (top right of workflow framework)

# test a workflow
A simple workflow is available at https://github.com/JeGoi/Armadillo_SNPs_WF_test \
It can be run on local machine with Docker or on Mammouth Parallèle II (https://wiki.ccs.usherbrooke.ca/Mammouth:Accueil)

# from armadillo1.1 to armadillo2.0
On mac or windows, you can use http://www.bioinfo.uqam.ca/armadillo/download.html \
Then replace ./src/ ./data/ ./lib/ directories by the directories from this depot \
Remove also ./config.dat and ./armadillo.log \
Remove ./Armadillo.jar. Rename ./Armadillo.jar_mac for Mac (and ./Armadillo.jar_win for Windows) in Armadillo.jar \
Then run Armadillo as before.
On linux, just follow instruction from here: https://github.com/JeGoi/Armadillo_SNPs_WF_test/blob/master/README.md or clone (download this depot) and use Armadillo.jar_lin as Armadillo.jar

# NGS not on Docker
The following programs are not on Docker, you will have to add their binary files in ./executable
- Bowtie
- Bowtie2
- Cutadapt
- FastqC
...
We expect to moved them on Docker quickly
