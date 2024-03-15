# Projet_Api_Evenements_Membres

L’application gère les événements d’une association et de ses membres.

Membre : un membre d’association est caractérisé par un id, son nom, son prénom, son âge son mail et son adresse. Il n’existe pas deux membres ayant le même id et adresse mail.

Événement : un événement est défini par un id, un nom, une date et une heure, une durée, un lieu et un nombre maximum de personnes y participant. Deux événements ne peuvent pas avoir lieu en même temps dans le même lieu.

Inscription : un membre peut s’inscrire à un événement. La contrainte est que le membre ne doit pas être déjà inscrit à un événement qui se chevauche dans le temps avec le nouvel événement auquel il veut s’inscrire. On ne peut également s’inscrire à un événement que si le nombre maximal de participants n’est pas atteint. Un membre peut se désinscrire d’un événement.

Fonctionnalités : on doit pouvoir visualiser l’ensemble des membres de l’association, l’ensemble des événements, l’ensemble des inscriptions pour un événement donné et pour chaque membre, pouvoir lister les événements auxquels il est inscrit. L’application permet de créer, modifier et supprimer de nouveaux membres ou événements.

Technique :
- Ces API ont été codées en Spring
- JDK-17 à utiliser

Membre du groupe :

- CharlotteMenou : Charlotte Menou
- Kirawashi : Léo Paugam
- NathanaelMo : Nathanaël Monnier
