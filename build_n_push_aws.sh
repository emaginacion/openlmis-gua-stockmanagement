#!/bin/bash
docker-compose -f docker-compose.builder.yml run builder
docker-compose -f docker-compose.builder.yml build image
docker tag openlmis/stockmanagement:latest public.ecr.aws/m5t3b8m1/openlmis-stockmanagement:latest
aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws
docker push public.ecr.aws/m5t3b8m1/openlmis-stockmanagement:latest