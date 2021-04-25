## Weixin Backend
### Dev Flow
#### Branchs
- `master`: production
- `dev`: development

#### Commit Msgs
Use **conventional commit msgs**

Refs:
- [https://www.conventionalcommits.org/en/v1.0.0/](https://www.conventionalcommits.org/en/v1.0.0/)


### Deploy
```bash
# Http 8000
# Websocket 7999
docker exec course /bin/bash
cd ~/weixin_backend
./scripts/start.sh
```




