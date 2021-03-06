package com.mrpowergamerbr.loritta.commands;

import lombok.Getter;

@Getter
public enum CommandCategory {
    FUN("Diversão", "Quer um pouco de entreterimento? Quer animar o chat do seu servidor com várias coisas engraçadas e aleatórias? Então use os comandos de diversão!"),
    MINECRAFT("Minecraft", "Comandos relacionados ao Minecraft"),
    POKEMON("Pokémon", "Comandos relacionados ao Pokémon"),
    UNDERTALE("Undertale", "Comandos relacionados ao Undertale"),
    DISCORD("Discord", "Comandos relacionados ao Discord"),
    MISC("Miscelânea", "Comandos que até hoje não encontraram uma categoria específica para viver... Aqui você irá encontrar vários comandos que não se encaixam nas outras categorias!"),
    ADMIN("Administração", "Comandos para a administração de servidores/guilds"),
    UTILS("Utilitários", "Comandos úteis para facilitar a sua vida!"),
    SOCIAL("Social", "Sims são criaturas sociáveis e precisam de interação social. E para isto você pode interagir com a sua família, amigos, vizinhos e até mesmo com os membros do seu servidor no Discord. E, acredite, os meus comandos são um ótimo instrumento para interagir com outras pessoas!"),
    MUSIC("Música", "Comandos relacionados a DJ Loritta"),
    MAGIC("Mágica", "Comandos que você nunca deverá ver na sua vida"); // Esta categoria é usada para comandos APENAS para o dono do bot (no caso, MrPowerGamerBR#4185)

    public String fancyTitle;
    public String description;

    CommandCategory(String fancyTitle, String description) {
        this.fancyTitle = fancyTitle;
        this.description = description;
    }
}
