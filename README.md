# uOlhoDeus

Uma simples cópia do plugin de Olho de Deus da Rede Stone.

## Comandos

**•** */giveolho* - Comando para pegar Olho de Deus.

## Permissões

**•** *olhodeus.admin* - Poder utilizar os comandos de give e reload.

## Configuração

### Padrão

```java
Tempo: 10
VoltarSpawn: true
#Se desativar volta para última posição quando usou o item

Item:
  Material: 381
  Data: 0
  Nome: '&cOlho de Deus'
  Glow: true
  Lore:
  - ''
  - '&7Nada pode se esconder do Olho de Deus'
  - '&7Duração: &f10s&7.'
  - ''
  - '&e* Utilizado para invasões'

Mensagens:
  Title: ''
  SubTitle: '&4&lOlho de Deus'
  AcabouSpawn: '&cSeu tempo de Olho de Deus acabou e por isto você foi levado ao spawn!'
  AcabouVoltar: '&cSeu tempo de Olho de Deus acabou e por isto você foi levado ao último local que você usou o item!'
  SemPerm: '&cVocê não possui permissão.'
  FaltaArgumento: '&cVocê deve utilizar /giveolho <jogador> <quantia>.'
  Adicionado: '&eFoi adicionado &f{quantia}x Olho de Deus &eno inventário de &f{player} &ecom sucesso!'
  SomenteSurvival: '&cVocê precisa estar no modo de jogo SURVIVAL para usar o item.'

ActionBar:
  Ativar: true
  Mensagens:
    Ativar: '&aOlho de Deus ativado com sucesso!'
    Desativar: '&cOlho de Deus desativado com sucesso!'
