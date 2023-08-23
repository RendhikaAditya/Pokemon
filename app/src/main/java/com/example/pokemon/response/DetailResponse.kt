package com.example.pokemon.response

data class DetailResponse(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val game_indices: List<GameIndice>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
){
    data class Ability(
        val ability: AbilityX,
        val is_hidden: Boolean,
        val slot: Int
    ){
        data class AbilityX(
            val name: String,
            val url: String
        )

    }

    data class Form(
        val name: String,
        val url: String
    )

    data class GameIndice(
        val game_index: Int,
        val version: Version
    ){
        data class Version(
            val name: String,
            val url: String
        )
    }

    data class Move(
        val move: MoveX,
        val version_group_details: List<VersionGroupDetail>
    ){
        data class MoveX(
            val name: String,
            val url: String
        )

        data class VersionGroupDetail(
            val level_learned_at: Int,
            val move_learn_method: MoveLearnMethod,
            val version_group: VersionGroup
        ){
            data class MoveLearnMethod(
                val name: String,
                val url: String
            )
            data class VersionGroup(
                val name: String,
                val url: String
            )

        }

    }

    data class Stat(
        val base_stat: Int,
        val effort: Int,
        val stat: StatX
    ){
        data class StatX(
            val name: String,
            val url: String
        )
    }

    data class Type(
        val slot: Int,
        val type: TypeX
    ){
        data class TypeX(
            val name: String,
            val url: String
        )
    }

    data class Species(
        val name: String,
        val url: String
    )

    data class Sprites(
        val back_default: String,
        val back_female: Any,
        val back_shiny: String,
        val back_shiny_female: Any,
        val front_default: String,
        val front_female: Any,
        val front_shiny: String,
        val front_shiny_female: Any,
        val other: Other,
        val versions: Versions
    ){
        data class Other(
            val dream_world: DreamWorld,
            val home: Home,
            val officialArtwork: OfficialArtwork
        ){
            data class DreamWorld(
                val front_default: String,
                val front_female: Any
            )
            data class Home(
                val front_default: String,
                val front_female: Any,
                val front_shiny: String,
                val front_shiny_female: Any
            )
            data class OfficialArtwork(
                val front_default: String,
                val front_shiny: String
            )

        }
        data class Versions(
            val generationi: GenerationI,
            val generationii: GenerationIi,
            val generationiii: GenerationIii,
            val generationiv: GenerationIv,
            val generationv: GenerationV,
            val generationvi: GenerationVi,
            val generationvii: GenerationVii,
            val generationviii: GenerationViii
        ){
            data class GenerationI(
                val redblue: RedBlue,
                val yellow: Yellow
            ){
                data class RedBlue(
                    val back_default: String,
                    val back_gray: String,
                    val back_transparent: String,
                    val front_default: String,
                    val front_gray: String,
                    val front_transparent: String
                )

                data class Yellow(
                    val back_default: String,
                    val back_gray: String,
                    val back_transparent: String,
                    val front_default: String,
                    val front_gray: String,
                    val front_transparent: String
                )
            }

            data class GenerationIi(
                val crystal: Crystal,
                val gold: Gold,
                val silver: Silver
            ){

                data class Crystal(
                    val back_default: String,
                    val back_shiny: String,
                    val back_shiny_transparent: String,
                    val back_transparent: String,
                    val front_default: String,
                    val front_shiny: String,
                    val front_shiny_transparent: String,
                    val front_transparent: String
                )

                data class Gold(
                    val back_default: String,
                    val back_shiny: String,
                    val front_default: String,
                    val front_shiny: String,
                    val front_transparent: String
                )

                data class Silver(
                    val back_default: String,
                    val back_shiny: String,
                    val front_default: String,
                    val front_shiny: String,
                    val front_transparent: String
                )

            }

            data class GenerationIii(
                val emerald: Emerald,
                val fireredleafgreen: FireredLeafgreen,
                val rubysapphire: RubySapphire
            ){
                data class Emerald(
                    val front_default: String,
                    val front_shiny: String
                )

                data class FireredLeafgreen(
                    val back_default: String,
                    val back_shiny: String,
                    val front_default: String,
                    val front_shiny: String
                )

                data class RubySapphire(
                    val back_default: String,
                    val back_shiny: String,
                    val front_default: String,
                    val front_shiny: String
                )

            }

            data class GenerationIv(
                val diamondpearl: DiamondPearl,
                val heartgoldsoulsilver: HeartgoldSoulsilver,
                val platinum: Platinum
            ){
                data class DiamondPearl(
                    val back_default: String,
                    val back_female: Any,
                    val back_shiny: String,
                    val back_shiny_female: Any,
                    val front_default: String,
                    val front_female: Any,
                    val front_shiny: String,
                    val front_shiny_female: Any
                )
                data class HeartgoldSoulsilver(
                    val back_default: String,
                    val back_female: Any,
                    val back_shiny: String,
                    val back_shiny_female: Any,
                    val front_default: String,
                    val front_female: Any,
                    val front_shiny: String,
                    val front_shiny_female: Any
                )
                data class Platinum(
                    val back_default: String,
                    val back_female: Any,
                    val back_shiny: String,
                    val back_shiny_female: Any,
                    val front_default: String,
                    val front_female: Any,
                    val front_shiny: String,
                    val front_shiny_female: Any
                )

            }

            data class GenerationV(
                val blackwhite: BlackWhite
            ){
                data class BlackWhite(
                    val animated: Animated,
                    val back_default: String,
                    val back_female: Any,
                    val back_shiny: String,
                    val back_shiny_female: Any,
                    val front_default: String,
                    val front_female: Any,
                    val front_shiny: String,
                    val front_shiny_female: Any
                ){
                    data class Animated(
                        val back_default: String,
                        val back_female: Any,
                        val back_shiny: String,
                        val back_shiny_female: Any,
                        val front_default: String,
                        val front_female: Any,
                        val front_shiny: String,
                        val front_shiny_female: Any
                    )

                }

            }

            data class GenerationVi(
                val omegarubyalphasapphire: OmegarubyAlphasapphire,
                val xy: XY
            ){
                data class OmegarubyAlphasapphire(
                    val front_default: String,
                    val front_female: Any,
                    val front_shiny: String,
                    val front_shiny_female: Any
                )
                data class XY(
                    val front_default: String,
                    val front_female: Any,
                    val front_shiny: String,
                    val front_shiny_female: Any
                )

            }

            data class GenerationVii(
                val icons: Icons,
                val ultrasunultramoon: UltraSunUltraMoon
            ){

                data class Icons(
                    val front_default: String,
                    val front_female: Any
                )

                data class UltraSunUltraMoon(
                    val front_default: String,
                    val front_female: Any,
                    val front_shiny: String,
                    val front_shiny_female: Any
                )

            }

            data class GenerationViii(
                val icons: Icons
            ){

                data class Icons(
                    val front_default: String,
                    val front_female: Any
                )
            }

        }

    }

}







































