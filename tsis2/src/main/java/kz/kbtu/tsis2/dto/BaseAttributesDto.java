package kz.kbtu.tsis2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BaseAttributesDto(
        @JsonProperty("movement_speed") String movementSpeed,
        @JsonProperty("physical_attack") String physicalAttack,
        @JsonProperty("physical_defense") String physicalDefense,
        @JsonProperty("physical_lifesteal") String physicalLifesteal,
        @JsonProperty("physical_penetration") String physicalPenetration,

        @JsonProperty("magic_attack") String magicAttack,
        @JsonProperty("magic_power") String magicPower,
        @JsonProperty("magic_penetration") String magicPenetration,
        @JsonProperty("magic_lifesteal") String magicLifesteal,

        @JsonProperty("spell_vamp") String spellVamp,
        @JsonProperty("magic_resistance") String magicResistance,

        String hp,
        String mana,

        @JsonProperty("attack_speed") String attackSpeed,
        @JsonProperty("hp_regen_rate") String hpRegenRate,
        @JsonProperty("mana_regen_rate") String manaRegenRate,

        @JsonProperty("critical_strike_chance") String criticalStrikeChance,
        @JsonProperty("critical_damage") String criticalDamage,

        @JsonProperty("cd_reduction") String cdReduction,
        @JsonProperty("bs_cd_reduction") String bsCdReduction,

        @JsonProperty("healing_effect") String healingEffect,

        @JsonProperty("monster_damage_reduction") String monsterDamageReduction,
        @JsonProperty("monster_damage") String monsterDamage
) {}
