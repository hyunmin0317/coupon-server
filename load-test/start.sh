#!/bin/bash

echo "================== SERVER DOWN =================="
sudo docker-compose down

echo "================== SERVER UP  =================="
sudo docker-compose up -d --scale worker=3
