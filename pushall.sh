#!/bin/bash

if [ ! -d ".git" ]; then
    echo "Not a git repository"
else
    (cd "./CAS Project"; mvn clean)

    git add .
    git commit -m "$1"
    git push $2
fi
